import aQute.bnd.gradle.BundleTaskConvention

plugins {
	`kotlin-library-conventions`
	`java-test-fixtures`
}

description = "JUnit Jupiter API"

dependencies {
	api(platform(projects.junitBom))
	api(libs.opentest4j)
	api(projects.junitPlatformCommons)

	compileOnlyApi(libs.apiguardian)

	compileOnly(kotlin("stdlib"))
}

tasks {
	jar {
		withConvention(BundleTaskConvention::class) {
			bnd("""
				Require-Capability:\
					org.junit.platform.engine;\
						filter:='(&(org.junit.platform.engine=junit-jupiter)(version>=${'$'}{version_cleanup;${rootProject.property("version")!!}})(!(version>=${'$'}{versionmask;+;${'$'}{version_cleanup;${rootProject.property("version")!!}}})))';\
						effective:=active
			""")
		}
	}
}
