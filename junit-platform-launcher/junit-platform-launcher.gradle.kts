import aQute.bnd.gradle.BundleTaskConvention

plugins {
	`java-library-conventions`
	`java-test-fixtures`
}

description = "JUnit Platform Launcher"

dependencies {
	api(platform(projects.junitBom))
	api(projects.junitPlatformEngine)

	compileOnlyApi(libs.apiguardian)
}

tasks {
	jar {
		withConvention(BundleTaskConvention::class) {
			bnd("""
				Provide-Capability:\
					org.junit.platform.launcher;\
						org.junit.platform.launcher='junit-platform-launcher';\
						version:Version="${'$'}{version_cleanup;${project.version}}"
			""")
		}
	}
}
