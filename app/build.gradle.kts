plugins {
    application
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    implementation("com.google.guava:guava:31.1-jre")
}

application {
    mainClass.set("whatsappwrapped.AppMain")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.jar {
    enabled = false
}

// Build proces disabled - use shadowjar

// tasks.jar {
//     archiveFileName.set("WhatsappWrapped.jar")
//     duplicatesStrategy = DuplicatesStrategy.INCLUDE
//     archiveFileName.set("$buildDir/libs/WhatsappWrapped.jar")
//     manifest {
//         attributes["Main-Class"] = "whatsappwrapped.AppMain"
//     }
// }

tasks.shadowJar {
    mergeServiceFiles()
    archiveClassifier.set("all")
    archiveFileName.set("WhatsappWrapped.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
