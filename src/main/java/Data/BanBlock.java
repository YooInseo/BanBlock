package Data;


import org.bukkit.Material;
import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanBlock {

    private Material material;
    private OfflinePlayer player;
    private int Loc;
    private int BanBlockLoc;
    private int Page;


    public BanBlock(OfflinePlayer player, Material material, int Loc, int Page) {
        this.material = material;
        this.player = player;
        this.Loc = Loc;
        this.Page = Page;
    }

    public void setBanBlockLoc(int banBlockLoc) {
        BanBlockLoc = banBlockLoc;
    }
    public int getBanblockLoc(){
        return BanBlockLoc;
    }


    public void setPage(int page) {
        Page = page;
    }

    public Material getMaterial() {
        return material;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public int getPage() {
        return Page;
    }
}
