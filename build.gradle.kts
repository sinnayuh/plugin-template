plugins {
    id("java")
    application
    id("org.jetbrains.kotlin.jvm") version("1.9.0")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "codes.sinister"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}

application {
    mainClass.set("codes.sinister.mc.Main")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveBaseName.set("plugin")
        archiveVersion.set("1.0.0-SNAPSHOT")
        archiveClassifier.set("")
        configurations = listOf(project.configurations.getByName("runtimeClasspath"))
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    jar {
        manifest {
            attributes["Main-Class"] = application.mainClass
        }
    }
}