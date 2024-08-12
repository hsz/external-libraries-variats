plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

configurations {
    register("composedJar") {
        isCanBeConsumed = true
        isCanBeResolved = false
        isVisible = false

        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, project.objects.named(Bundling.EXTERNAL))
            attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category.LIBRARY))
            attributeProvider(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, project.provider {
                project.the<JavaPluginExtension>().targetCompatibility.majorVersion.toInt()
            })
            attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage.JAVA_RUNTIME))
            attributes.attribute(Attribute.of("org.gradle.jvm.environment", String::class.java), "standard-jvm")
            attributes.attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")

            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named(LibraryElements::class.java, "composed-jar"))
        }
    }
}

tasks {
    jar {
        from(file("marker_base.txt"))
    }
    register<Jar>("composeJar") {
        archiveClassifier = "composed"

        from(zipTree(jar.flatMap { it.archiveFile }))
        from(file("marker_composed.txt"))
        dependsOn(jar)
    }
}

project.artifacts.add("composedJar", tasks.named("composeJar"))
