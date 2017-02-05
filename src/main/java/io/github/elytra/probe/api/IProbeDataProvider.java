package io.github.elytra.probe.api;

import java.util.List;

/**
 * Capability representing an object's ability to provide detailed information to some kind of probe or UI element. The
 * simple case is something like WAILA, HWYLA, or TheOneProbe. However, this API could be used by snap-on monitor
 * blocks, remote monitoring systems, or data-based automation. Picture this: "activate redstone when bar labeled
 * 'temperature' is greater than 80%". Structured data is very useful data.
 * 
 * <p>This interface addresses how you get the data. What you do with it is up to you.
 * 
 * <p>Probes and other devices should gather data only on the server Side. Implementors are encouraged to ignore
 * clientside probe requests.
 */
public interface IProbeDataProvider {
	public void provideProbeData(List<IProbeData> data);
}
