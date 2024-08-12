plugins {
    kotlin("jvm") version "2.0.10"
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":module"))

    attributesSchema {
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE) {
            compatibilityRules.add(ComposedJarRule::class)
        }
    }
}

configurations {
    runtimeClasspath {
        attributes {
            // Uncomment the following line to request for the composed variant
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named(LibraryElements::class.java, "composed-jar"))
        }
    }
}

abstract class ComposedJarRule : AttributeCompatibilityRule<LibraryElements> {

    override fun execute(details: CompatibilityCheckDetails<LibraryElements>) = details.run {
        if (consumerValue?.name == "composed-jar" && producerValue?.name == "jar") {
            compatible()
        }
    }
}
