import org.lwjgl.Lwjgl
import org.lwjgl.Release
import org.lwjgl.lwjgl
import org.lwjgl.sonatype

plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("org.lwjgl.plugin") version "0.0.35"
}

repositories {
    mavenCentral()
    sonatype()
}

dependencies {
    lwjgl {
        version = Release.latest
        implementation(Lwjgl.Preset.minimalOpenGL + Lwjgl.Module.jawt)
    }
    implementation("org.lwjglx:lwjgl3-awt:0.2.3") {
        exclude(group = "org.lwjgl")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.AppKt"
}
