plugins {
    kotlin("jvm") version "2.0.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":module"))
}

configurations {
    runtimeClasspath {
        attributes {
            // Uncomment the following line to request for the composed variant
            // attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named("composed-jar"))
        }
    }
}

kotlin {
    jvmToolchain(17)
}
