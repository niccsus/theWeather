import javax.swing.ImageIcon;
import javax.swing.JLabel;

interface Weather_Data {
    public int get_wind_speed();
    public String get_icon_url();
    public ImageIcon get_icon();
    public int get_humidity();
    public int get_pressure();
    public int get_cloud_percent();

}
