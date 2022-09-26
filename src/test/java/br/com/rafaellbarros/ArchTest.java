package br.com.rafaellbarros;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * created by:
 *
 * @author rafael barros for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 23/09/2022
 */

@QuarkusTest
public class ArchTest {

   private JavaClasses importedClasses;

   @BeforeEach
   public void setup() {
      importedClasses = new ClassFileImporter()
              .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
              .importPackages("br.com.rafaellbarros");
   }

   /*    Package Dependency Checks*/

   /* servicesERepositoriesShouldNotDependOnWebLayer */
   @Test
   void servicesERepositoriesNaoDevemDependerDaCamadaWeb() {

      noClasses()
              .that().resideInAnyPackage("br.com.rafaellbarros.service..")
              .or().resideInAnyPackage("br.com.rafaellbarros.repository..")
              .should()
              .dependOnClassesThat()
              .resideInAnyPackage("br.com.rafaellbarros.rest..")
              .because("Services e repositories should não devem depender da camada da web")
              .check(importedClasses);
   }

   /* Class Dependency Checks*/

   /* serviceClassesShouldOnlyBeAccessedByRest */
   @Test
   void serviceClassesDevemSerAcessadoApenasPeloRest() {
      classes()
              .that().resideInAPackage("..service..")
              .should().onlyBeAccessed().byAnyPackage("..service..", "..rest..")
              .check(importedClasses);
   }

   /* naming convention */

   /* repositoryClassesShouldBeNamedXRepository */
   @Test
   void repositoryClassesDevemSerChamadosXRepository() {
      classes()
              .that().resideInAPackage("..repository")
              .should().haveSimpleNameEndingWith("Repository")
              .check(importedClasses);
   }

   /* repositoryClassesShouldHaveSpringRepositoryAnnotation */
   @Test
   void repositoryClassesNaoDevemTerAnotacaoSpringRepository() {
      noClasses()
              .that().resideInAPackage("..repository")
              .should().beAnnotatedWith(Repository.class)
              .check(importedClasses);
   }

   /* serviceClassShouldUseApplicationScopedAnnotation */
   // TODO: verify ApplicationScoped or Singleton and annotations CDI inject
   @Test
   void serviceClassessDevemTerAnotacaoSpringService() {
      classes()
              .that().resideInAPackage("..service..")
              .should()
              .beAnnotatedWith(ApplicationScoped.class)
              .check(importedClasses);
   }

   /* Layer Dependency Rules Test */

   /* layeredArchitectureShouldBeRespected */
   @Test
   void arquiteturaEmCamadasDeveSerRespeitada() {
      layeredArchitecture()
              .layer("Rest").definedBy("..rest..")
              .layer("Service").definedBy("..service..")
              .layer("Repository").definedBy("..repository..")

              .whereLayer("Rest").mayNotBeAccessedByAnyLayer()
              .whereLayer("Service").mayOnlyBeAccessedByLayers("Rest")
              .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
              .check(importedClasses);
   }

   /* repositoriesMustResideInRepositoryPackage */
   @Test
   void repositoriesDevemEstarPresenteNoPacoteRepository() {
      classes().that().haveNameMatching(".*Repository").should().resideInAPackage("..repository..")
              .as("Repositories devem estar em um pacote '..repository..'")
              .check(importedClasses);
   }

   /* servicesMustResideInServicePackage */
   @Test
   void servicesDevemEstarPresenteNoPacoteService() {
      classes().that().haveNameMatching(".*Service").should().resideInAPackage("..service..")
              .as("Services devem estar em um pacote '..service..'")
              .check(importedClasses);
   }

   /* entitiesMustResideInEntityPackage  */
   // TODO: lançar a versão 2.0 igual do cleanarc


   @Test
   void entitiesDevemEstarPresenteNoPacoteModelEntity() {
      classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..model.entity..")
              .andShould().haveSimpleNameEndingWith("Entity")
              .as("Etities devem estar em um pacote '..model.entity..'")
              .check(importedClasses);
   }


   /* enumsMustResideInEnumsPackage  */
   /*
   @Test
   void enumsDevemEstarPresenteNoPacoteModelEnums() {
      classes()
              .that().resideInAPackage("..model.enums..")
              .should()
              .beEnums()
              .as("Enums devem estar em um pacote '..model.enums..'")
              .check(importedClasses);
   } */

   /* controllersMustResideInServicePackage */
   @Test
   void controllersDevemEstarPresenteNoPacoteRest() {
      classes().that().haveNameMatching(".*Rest").should().resideInAPackage("..rest..")
              .as("Rets devem estar em um pacote '..rest..'")
              .check(importedClasses);
   }

   /* interfacesShouldNotHaveNamesEndingWithTheWordInterface */
   @Test
   void interfacesNaoDevemTerNomesQueTerminemComAPalavraInterface() {
      noClasses().that().areInterfaces().should().haveNameMatching(".*Interface").check(importedClasses);
   }

}
