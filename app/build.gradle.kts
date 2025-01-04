plugins {
    kotlin("jvm") version "2.0.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.5"))
    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-jawt")
    implementation("org.lwjgl", "lwjgl-opengl")
    implementation("org.lwjgl", "lwjgl-vulkan")

    implementation("org.lwjglx:lwjgl3-awt:0.2.3") {
        exclude(group = "org.lwjgl")
    }

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-linux")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-linux")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-linux-arm64")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-linux-arm64")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-macos")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-macos")
    runtimeOnly("org.lwjgl", "lwjgl-vulkan", classifier = "natives-macos")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-macos-arm64")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-macos-arm64")
    runtimeOnly("org.lwjgl", "lwjgl-vulkan", classifier = "natives-macos-arm64")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-windows")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-windows")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "natives-windows-arm64")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "natives-windows-arm64")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.AppKt"
}
