package {{cookiecutter.repo_name}};

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.junit.BeforeClass;
import org.junit.Test;

public class PluginTest {
    private static Logger logger;
    private static Plugin plugin;

    @BeforeClass
    public static void setUp() throws Exception {
        TestHelper.setUp();
        logger = TestHelper.getLogger();
        plugin = TestHelper.getPlugin();
    }

    @Test
    public void testOnEnable() {
        plugin.onEnable();
        verify(logger).info("Enabled!");
    }

    @Test
    public void testOnDisable() {
        plugin.onDisable();
        verify(logger).info("Disabled!");
    }

    @Test
    public void testValidCommand() {
        Player playerMock = mock(Player.class);
        Command cmdMock = mock(Command.class);
        when(cmdMock.getName()).thenReturn("test");
        Boolean valid = plugin.onCommand(playerMock, cmdMock, "test", new String[] {});
        verify(playerMock).sendMessage(ChatColor.AQUA + "Hello, World!");
        assertTrue(valid);
    }

    @Test
    public void testInvalidCommand() {
        Player playerMock = mock(Player.class);
        Command cmdMock = mock(Command.class);
        when(cmdMock.getName()).thenReturn("invalid");
        Boolean valid = plugin.onCommand(playerMock, cmdMock, "invalid", new String[] {});
        assertFalse(valid);
    }
}
