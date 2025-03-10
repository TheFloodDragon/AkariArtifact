import io.izzel.taboolib.gradle.*

plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "2.0.22"
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
}

taboolib {
    env {
        install(
            Metrics,
            CommandHelper,
            Kether,
            JavaScript,
            Bukkit,
            BukkitUI,
            BukkitNMS,
            BukkitNMSUtil,
            BukkitHook,
            BukkitUtil
        )
    }
    description {
        contributors {
            name("CPJiNan")
            name("QFeng")
        }
        dependencies {
            name("Vault").optional(true)
            name("PlayerPoints").optional(true)
            name("PlaceholderAPI").optional(true)
            name("MythicMobs").optional(true)
            name("SX-Attribute").optional(true)
        }
    }
    version { taboolib = "6.2.2" }
    relocate("ink.ptms.um", "com.github.cpjinan.plugin.akariartifact.um")
}

repositories {
    mavenCentral()
    maven(url = "https://mvn.lumine.io/repository/maven-public/")
    maven("https://r.irepo.space/maven/")
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11200:11200")
    compileOnly("pers.neige.neigeitems:NeigeItems:1.20.35")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    taboo("ink.ptms:um:1.0.1")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}