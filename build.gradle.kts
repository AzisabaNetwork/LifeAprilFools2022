plugins {
    java
}

group = "net.azisaba.aprilFools2022"
version = "1.0.0"

repositories {
    mavenCentral()
}

subprojects {
    group = parent!!.group
    version = parent!!.version

    apply {
        plugin("java")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://repo.blueberrymc.net/repository/maven-public/") }
    }

    dependencies {
        if (name != "common") implementation(project(":common"))
        compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
        compileOnly("org.jetbrains:annotations:22.0.0")
        compileOnly("io.netty:netty-all:4.1.68.Final")
    }
}

allprojects {
    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
    }
}
