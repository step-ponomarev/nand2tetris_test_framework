plugins {
    id("java")
    id("maven-publish")
}

group = "com.gitgub.step-ponomarev"
version = "1.0.0"

val artifactName = "nand2tetris-test-lib"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
                "Implementation-Title" to "Test lib for nand2tetris course",
                "Implementation-Version" to version
        )
    }
    from(sourceSets.main.get().output)
    archiveBaseName.set(artifactName)
}

// Задача build будет зависеть от jar
tasks.build {
    dependsOn(tasks.jar)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "local"
            url = uri("file://${buildDir}/repo") // Путь к локальному репозиторию
        }
    }
}
