# ProbeDataProviderAPI

A Capability and associated gadgetry for providing structured data
to mods, for automation, informational HUD elements, and much more.

Structured data is very useful!

## Using ProbeDataProviderAPI

The IProbeDataProvider interface is intended to be used as a
standard Forge capability. [FruitPhone](https://github.com/elytra/FruitPhone)
is essentially a reference implementation for a data *reader*.

Here's what you need to add to your build.gradle:

```gradle
repositories {
	maven {
		url = 'https://repo.elytradev.com'
	}
}

dependencies {
	deobfCompile 'com.elytradev:probedataproviderapi:MC1.12_ver1.1.1'
}
```
