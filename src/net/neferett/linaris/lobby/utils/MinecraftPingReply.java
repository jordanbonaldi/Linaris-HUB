package net.neferett.linaris.lobby.utils;

import java.util.List;

public class MinecraftPingReply
{
  private String description;
  private Players players;
  private Version version;
  private String favicon;

  public String getDescription()
  {
    return this.description;
  }

  public Players getPlayers()
  {
    return this.players;
  }

  public Version getVersion()
  {
    return this.version;
  }

  public String getFavicon()
  {
    return this.favicon;
  }

  public class Player
  {
    private String name;
    private String id;

    public Player()
    {
    }

    public String getName()
    {
      return this.name;
    }

    public String getId()
    {
      return this.id;
    }
  }

  public class Players
  {
    private int max;
    private int online;
    private List<MinecraftPingReply.Player> sample;

    public Players()
    {
    }

    public int getMax()
    {
      return this.max;
    }

    public int getOnline()
    {
      return this.online;
    }

    public List<MinecraftPingReply.Player> getSample()
    {
      return this.sample;
    }
  }

  public class Version
  {
    private String name;
    private int protocol;

    public Version()
    {
    }

    public String getName()
    {
      return this.name;
    }

    public int getProtocol()
    {
      return this.protocol;
    }
  }
}