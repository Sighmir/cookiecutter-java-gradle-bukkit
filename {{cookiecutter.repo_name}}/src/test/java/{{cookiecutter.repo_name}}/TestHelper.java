package {{cookiecutter.repo_name}};

import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.objenesis.ObjenesisHelper;

public class TestHelper {
  private static Logger logger;
  private static Server server;
  private static Plugin plugin;

  private static void setUpLogger() {
    logger = spy(Logger.getGlobal());
  }

  private static void setUpServer() {
    server = mock(Server.class);
    when(server.getLogger()).thenReturn(logger);
    when(server.isPrimaryThread()).thenReturn(true);
    when(server.getName()).thenReturn("TestServer");
    when(server.getVersion()).thenReturn("{{ cookiecutter.api_version }}");
    when(server.getBukkitVersion()).thenReturn("{{ cookiecutter.api_version }}");
    Bukkit.setServer(server);
  }

  @SuppressWarnings({ "deprecation" })
  private static void setUpPlugin() throws Exception {
    File dataDir = new File("build/tmp/spigotPluginYaml");
    File jar = new File("build/libs", "{{ cookiecutter.repo_name }}.jar");
    assumeTrue(jar.exists());

    plugin = spy(ObjenesisHelper.newInstance(Plugin.class));
    doReturn(logger).when(plugin).getCustomLogger();

    PluginDescriptionFile pdf = new PluginDescriptionFile(new FileInputStream(new File(dataDir, "plugin.yml")));

    Method init = JavaPlugin.class.getDeclaredMethod("init", PluginLoader.class, Server.class,
        PluginDescriptionFile.class, File.class, File.class, ClassLoader.class);
    init.setAccessible(true);
    init.invoke(plugin, new JavaPluginLoader(server), server, pdf, dataDir, jar, plugin.getClass().getClassLoader());
  }

  public static void setUp() throws Exception {
    setUpLogger();
    setUpServer();
    setUpPlugin();
  }

  public static Logger getLogger() throws Exception {
    if (logger == null) {
      throw new Exception("TestHelper not ready, run TestHelper.setUp() first!");
    }
    return logger;
  }

  public static Server getServer() throws Exception {
    if (server == null) {
      throw new Exception("TestHelper not ready, run TestHelper.setUp() first!");
    }
    return server;
  }

  public static Plugin getPlugin() throws Exception {
    if (plugin == null) {
      throw new Exception("TestHelper not ready, run TestHelper.setUp() first!");
    }
    return plugin;
  }

}