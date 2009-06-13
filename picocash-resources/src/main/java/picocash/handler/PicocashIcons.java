/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package picocash.handler;

import java.util.TreeMap;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class PicocashIcons {

    private static final Log log = LogFactory.getLog(PicocashIcons.class);

    private static TreeMap<String, Icon> icons = new TreeMap<String, Icon>();
    private static TreeMap<String, Icon> systemIcons = new TreeMap<String, Icon>();

    private static Icon emptyIcon;

    public static void init() {
        icons.put("accept", getIconForName("/icons/accept.png"));
        icons.put("add", getIconForName("/icons/add.png"));
        icons.put("anchor", getIconForName("/icons/anchor.png"));
        icons.put("asterisk_orange", getIconForName("/icons/asterisk_orange.png"));
        icons.put("asterisk_yellow", getIconForName("/icons/asterisk_yellow.png"));
        icons.put("attach", getIconForName("/icons/attach.png"));
        icons.put("award_star_add", getIconForName("/icons/award_star_add.png"));
        icons.put("award_star_bronze_1", getIconForName("/icons/award_star_bronze_1.png"));
        icons.put("award_star_bronze_2", getIconForName("/icons/award_star_bronze_2.png"));
        icons.put("award_star_bronze_3", getIconForName("/icons/award_star_bronze_3.png"));
        icons.put("award_star_delete", getIconForName("/icons/award_star_delete.png"));
        icons.put("award_star_gold_1", getIconForName("/icons/award_star_gold_1.png"));
        icons.put("award_star_gold_2", getIconForName("/icons/award_star_gold_2.png"));
        icons.put("award_star_gold_3", getIconForName("/icons/award_star_gold_3.png"));
        icons.put("award_star_silver_1", getIconForName("/icons/award_star_silver_1.png"));
        icons.put("award_star_silver_2", getIconForName("/icons/award_star_silver_2.png"));
        icons.put("award_star_silver_3", getIconForName("/icons/award_star_silver_3.png"));
        icons.put("bell", getIconForName("/icons/bell.png"));
        icons.put("bin", getIconForName("/icons/bin.png"));
        icons.put("bomb", getIconForName("/icons/bomb.png"));
        icons.put("book", getIconForName("/icons/book.png"));
        icons.put("box", getIconForName("/icons/box.png"));
        icons.put("brick", getIconForName("/icons/brick.png"));
        icons.put("bricks", getIconForName("/icons/bricks.png"));
        icons.put("briefcase", getIconForName("/icons/briefcase.png"));
        icons.put("bug", getIconForName("/icons/bug.png"));
        icons.put("building", getIconForName("/icons/building.png"));
        icons.put("cake", getIconForName("/icons/cake.png"));
        icons.put("calculator", getIconForName("/icons/calculator.png"));
        icons.put("calendar", getIconForName("/icons/calendar.png"));
        icons.put("calendar_view_day", getIconForName("/icons/calendar_view_day.png"));
        icons.put("calendar_view_month", getIconForName("/icons/calendar_view_month.png"));
        icons.put("calendar_view_week", getIconForName("/icons/calendar_view_week.png"));
        icons.put("camera", getIconForName("/icons/camera.png"));
        icons.put("cancel", getIconForName("/icons/cancel.png"));
        icons.put("car", getIconForName("/icons/car.png"));
        icons.put("cart", getIconForName("/icons/cart.png"));
        icons.put("cd", getIconForName("/icons/cd.png"));
        icons.put("chart_bar", getIconForName("/icons/chart_bar.png"));
        icons.put("chart_pie", getIconForName("/icons/chart_pie.png"));
        icons.put("clock", getIconForName("/icons/clock.png"));
        icons.put("clock_red", getIconForName("/icons/clock_red.png"));
        icons.put("cog", getIconForName("/icons/cog.png"));
        icons.put("coins", getIconForName("/icons/coins.png"));
        icons.put("color_swatch", getIconForName("/icons/color_swatch.png"));
        icons.put("color_wheel", getIconForName("/icons/color_wheel.png"));
        icons.put("comment", getIconForName("/icons/comment.png"));
        icons.put("comments", getIconForName("/icons/comments.png"));
        icons.put("compress", getIconForName("/icons/compress.png"));
        icons.put("computer", getIconForName("/icons/computer.png"));
        icons.put("connect", getIconForName("/icons/connect.png"));
        icons.put("contrast", getIconForName("/icons/contrast.png"));
        icons.put("creditcards", getIconForName("/icons/creditcards.png"));
        icons.put("cross", getIconForName("/icons/cross.png"));
        icons.put("css", getIconForName("/icons/css.png"));
        icons.put("css_valid", getIconForName("/icons/css_valid.png"));
        icons.put("cup", getIconForName("/icons/cup.png"));
        icons.put("cursor", getIconForName("/icons/cursor.png"));
        icons.put("cut", getIconForName("/icons/cut.png"));
        icons.put("cut_red", getIconForName("/icons/cut_red.png"));
        icons.put("database", getIconForName("/icons/database.png"));
        icons.put("date", getIconForName("/icons/date.png"));
        icons.put("delete", getIconForName("/icons/delete.png"));
        icons.put("disconnect", getIconForName("/icons/disconnect.png"));
        icons.put("disk", getIconForName("/icons/disk.png"));
        icons.put("door", getIconForName("/icons/door.png"));
        icons.put("drink", getIconForName("/icons/drink.png"));
        icons.put("drive", getIconForName("/icons/drive.png"));
        icons.put("dvd", getIconForName("/icons/dvd.png"));
        icons.put("email", getIconForName("/icons/email.png"));
        icons.put("emoticon_evilgrin", getIconForName("/icons/emoticon_evilgrin.png"));
        icons.put("emoticon_grin", getIconForName("/icons/emoticon_grin.png"));
        icons.put("emoticon_happy", getIconForName("/icons/emoticon_happy.png"));
        icons.put("emoticon_smile", getIconForName("/icons/emoticon_smile.png"));
        icons.put("emoticon_surprised", getIconForName("/icons/emoticon_surprised.png"));
        icons.put("emoticon_tongue", getIconForName("/icons/emoticon_tongue.png"));
        icons.put("emoticon_unhappy", getIconForName("/icons/emoticon_unhappy.png"));
        icons.put("emoticon_waii", getIconForName("/icons/emoticon_waii.png"));
        icons.put("emoticon_wink", getIconForName("/icons/emoticon_wink.png"));
        icons.put("error", getIconForName("/icons/error.png"));
        icons.put("exclamation", getIconForName("/icons/exclamation.png"));
        icons.put("eye", getIconForName("/icons/eye.png"));
        icons.put("feed", getIconForName("/icons/feed.png"));
        icons.put("female", getIconForName("/icons/female.png"));
        icons.put("film", getIconForName("/icons/film.png"));
        icons.put("find", getIconForName("/icons/find.png"));
        icons.put("flag_blue", getIconForName("/icons/flag_blue.png"));
        icons.put("flag_green", getIconForName("/icons/flag_green.png"));
        icons.put("flag_orange", getIconForName("/icons/flag_orange.png"));
        icons.put("flag_pink", getIconForName("/icons/flag_pink.png"));
        icons.put("flag_purple", getIconForName("/icons/flag_purple.png"));
        icons.put("flag_red", getIconForName("/icons/flag_red.png"));
        icons.put("flag_yellow", getIconForName("/icons/flag_yellow.png"));
        icons.put("folder", getIconForName("/icons/folder.png"));
        icons.put("font", getIconForName("/icons/font.png"));
        icons.put("group", getIconForName("/icons/group.png"));
        icons.put("heart", getIconForName("/icons/heart.png"));
        icons.put("help", getIconForName("/icons/help.png"));
        icons.put("hourglass", getIconForName("/icons/hourglass.png"));
        icons.put("house", getIconForName("/icons/house.png"));
        icons.put("html", getIconForName("/icons/html.png"));
        icons.put("image", getIconForName("/icons/image.png"));
        icons.put("images", getIconForName("/icons/images.png"));
        icons.put("information", getIconForName("/icons/information.png"));
        icons.put("ipod", getIconForName("/icons/ipod.png"));
        icons.put("joystick", getIconForName("/icons/joystick.png"));
        icons.put("keyboard", getIconForName("/icons/keyboard.png"));
        icons.put("key", getIconForName("/icons/key.png"));
        icons.put("layers", getIconForName("/icons/layers.png"));
        icons.put("layout", getIconForName("/icons/layout.png"));
        icons.put("lightbulb", getIconForName("/icons/lightbulb.png"));
        icons.put("lightning", getIconForName("/icons/lightning.png"));
        icons.put("link", getIconForName("/icons/link.png"));
        icons.put("lock", getIconForName("/icons/lock.png"));
        icons.put("lorry", getIconForName("/icons/lorry.png"));
        icons.put("magnifier", getIconForName("/icons/magnifier.png"));
        icons.put("male", getIconForName("/icons/male.png"));
        icons.put("map", getIconForName("/icons/map.png"));
        icons.put("medal_bronze_1", getIconForName("/icons/medal_bronze_1.png"));
        icons.put("medal_bronze_2", getIconForName("/icons/medal_bronze_2.png"));
        icons.put("medal_bronze_3", getIconForName("/icons/medal_bronze_3.png"));
        icons.put("medal_gold_1", getIconForName("/icons/medal_gold_1.png"));
        icons.put("medal_gold_2", getIconForName("/icons/medal_gold_2.png"));
        icons.put("medal_gold_3", getIconForName("/icons/medal_gold_3.png"));
        icons.put("medal_silver_1", getIconForName("/icons/medal_silver_1.png"));
        icons.put("medal_silver_2", getIconForName("/icons/medal_silver_2.png"));
        icons.put("medal_silver_3", getIconForName("/icons/medal_silver_3.png"));
        icons.put("money_dollar", getIconForName("/icons/money_dollar.png"));
        icons.put("money_euro", getIconForName("/icons/money_euro.png"));
        icons.put("money", getIconForName("/icons/money.png"));
        icons.put("money_pound", getIconForName("/icons/money_pound.png"));
        icons.put("money_yen", getIconForName("/icons/money_yen.png"));
        icons.put("monitor", getIconForName("/icons/monitor.png"));
        icons.put("mouse", getIconForName("/icons/mouse.png"));
        icons.put("music", getIconForName("/icons/music.png"));
        icons.put("new", getIconForName("/icons/new.png"));
        icons.put("newspaper", getIconForName("/icons/newspaper.png"));
        icons.put("note", getIconForName("/icons/note.png"));
        icons.put("overlays", getIconForName("/icons/overlays.png"));
        icons.put("package", getIconForName("/icons/package.png"));
        icons.put("page", getIconForName("/icons/page.png"));
        icons.put("paintbrush", getIconForName("/icons/paintbrush.png"));
        icons.put("paintcan", getIconForName("/icons/paintcan.png"));
        icons.put("palette", getIconForName("/icons/palette.png"));
        icons.put("paste_plain", getIconForName("/icons/paste_plain.png"));
        icons.put("paste_word", getIconForName("/icons/paste_word.png"));
        icons.put("pencil", getIconForName("/icons/pencil.png"));
        icons.put("phone", getIconForName("/icons/phone.png"));
        icons.put("phone_sound", getIconForName("/icons/phone_sound.png"));
        icons.put("photo", getIconForName("/icons/photo.png"));
        icons.put("photos", getIconForName("/icons/photos.png"));
        icons.put("picture_add", getIconForName("/icons/picture_add.png"));
        icons.put("picture_delete", getIconForName("/icons/picture_delete.png"));
        icons.put("picture_edit", getIconForName("/icons/picture_edit.png"));
        icons.put("picture_empty", getIconForName("/icons/picture_empty.png"));
        icons.put("picture_error", getIconForName("/icons/picture_error.png"));
        icons.put("picture", getIconForName("/icons/picture.png"));
        icons.put("pictures", getIconForName("/icons/pictures.png"));
        icons.put("pilcrow", getIconForName("/icons/pilcrow.png"));
        icons.put("pill", getIconForName("/icons/pill.png"));
        icons.put("plugin", getIconForName("/icons/plugin.png"));
        icons.put("printer", getIconForName("/icons/printer.png"));
        icons.put("rainbow", getIconForName("/icons/rainbow.png"));
        icons.put("report", getIconForName("/icons/report.png"));
        icons.put("rosette", getIconForName("/icons/rosette.png"));
        icons.put("rss", getIconForName("/icons/rss.png"));
        icons.put("ruby", getIconForName("/icons/ruby.png"));
        icons.put("script", getIconForName("/icons/script.png"));
        icons.put("server", getIconForName("/icons/server.png"));
        icons.put("shading", getIconForName("/icons/shading.png"));
        icons.put("shield", getIconForName("/icons/shield.png"));
        icons.put("sitemap", getIconForName("/icons/sitemap.png"));
        icons.put("sound", getIconForName("/icons/sound.png"));
        icons.put("sport_8ball", getIconForName("/icons/sport_8ball.png"));
        icons.put("sport_basketball", getIconForName("/icons/sport_basketball.png"));
        icons.put("sport_football", getIconForName("/icons/sport_football.png"));
        icons.put("sport_golf", getIconForName("/icons/sport_golf.png"));
        icons.put("sport_raquet", getIconForName("/icons/sport_raquet.png"));
        icons.put("sport_shuttlcock", getIconForName("/icons/sport_shuttlecock.png"));
        icons.put("sport_soccer", getIconForName("/icons/sport_soccer.png"));
        icons.put("sport_tennis", getIconForName("/icons/sport_tennis.png"));
        icons.put("star", getIconForName("/icons/star.png"));
        icons.put("stop", getIconForName("/icons/stop.png"));
        icons.put("style", getIconForName("/icons/style.png"));
        icons.put("sum", getIconForName("/icons/sum.png"));
        icons.put("table", getIconForName("/icons/table.png"));
        icons.put("tab", getIconForName("/icons/tab.png"));
        icons.put("tag", getIconForName("/icons/tag.png"));
        icons.put("telephone", getIconForName("/icons/telephone.png"));
        icons.put("television", getIconForName("/icons/television.png"));
        icons.put("thumb_down", getIconForName("/icons/thumb_down.png"));
        icons.put("thumb_up", getIconForName("/icons/thumb_up.png"));
        icons.put("tick", getIconForName("/icons/tick.png"));
        icons.put("time", getIconForName("/icons/time.png"));
        icons.put("transmit", getIconForName("/icons/transmit.png"));
        icons.put("tux", getIconForName("/icons/tux.png"));
        icons.put("user_female", getIconForName("/icons/user_female.png"));
        icons.put("user_go", getIconForName("/icons/user_go.png"));
        icons.put("user_gray", getIconForName("/icons/user_gray.png"));
        icons.put("user_green", getIconForName("/icons/user_green.png"));
        icons.put("user_orange", getIconForName("/icons/user_orange.png"));
        icons.put("user", getIconForName("/icons/user.png"));
        icons.put("user_red", getIconForName("/icons/user_red.png"));
        icons.put("user_suit", getIconForName("/icons/user_suit.png"));
        icons.put("vcard", getIconForName("/icons/vcard.png"));
        icons.put("vector", getIconForName("/icons/vector.png"));
        icons.put("wand", getIconForName("/icons/wand.png"));
        icons.put("weather_clouds", getIconForName("/icons/weather_clouds.png"));
        icons.put("weather_cloudy", getIconForName("/icons/weather_cloudy.png"));
        icons.put("weather_lightning", getIconForName("/icons/weather_lightning.png"));
        icons.put("weather_rain", getIconForName("/icons/weather_rain.png"));
        icons.put("weather_snow", getIconForName("/icons/weather_snow.png"));
        icons.put("weather_sun", getIconForName("/icons/weather_sun.png"));
        icons.put("webcam", getIconForName("/icons/webcam.png"));
        icons.put("world", getIconForName("/icons/world.png"));
        icons.put("wrench", getIconForName("/icons/wrench.png"));
        icons.put("xhtml", getIconForName("/icons/xhtml.png"));
        icons.put("zoom", getIconForName("/icons/zoom.png"));

        systemIcons.put("minimize", getIconForName("/icons/minimize.png"));
        systemIcons.put("minimize-dark", getIconForName("/icons/minimize-dark.png"));
        systemIcons.put("maximize", getIconForName("/icons/maximize.png"));
        systemIcons.put("close", getIconForName("/icons/close.png"));
        systemIcons.put("split_vertical", getIconForName("/icons/application_tile_vertical.png"));
        systemIcons.put("split_horizontal", getIconForName("/icons/application_tile_horizontal.png"));
    }

    private static Icon getIconForName(String fileName) {
        ImageIcon icon = null;
        if (fileName != null) {
            try {
                icon = new ImageIcon(ImageIO.read(Class.forName(PicocashIcons.class.getName()).getResourceAsStream(fileName)));
            } catch (Exception ex) {
                log.info(ex);

            }
        }
        return icon;
    }

    public static Vector<String> getIcons() {
        return new Vector<String>(icons.keySet());
    }

    public static Icon getIcon(String name) {

        if (emptyIcon == null) {
            try {
                emptyIcon = new ImageIcon(ImageIO.read(Class.forName(PicocashIcons.class.getName()).getResourceAsStream("/icons/empty.png")));
            } catch (Exception ex) {
                log.fatal("can't load empty.png", ex);
            }
        }

        if (name == null) {
            return emptyIcon;
        }

        Icon icon = icons.get(name);
        if (icon == null) {
            return emptyIcon;
        } else {
            return icon;
        }

    }
    public static Icon getSystemIcon(String name) {

        if (emptyIcon == null) {
            try {
                emptyIcon = new ImageIcon(ImageIO.read(Class.forName(PicocashIcons.class.getName()).getResourceAsStream("/icons/empty.png")));
            } catch (Exception ex) {
                log.fatal("can't load empty.png", ex);
            }
        }

        if (name == null) {
            return emptyIcon;
        }

        Icon icon = systemIcons.get(name);
        if (icon == null) {
            return emptyIcon;
        } else {
            return icon;
        }

    }

}
