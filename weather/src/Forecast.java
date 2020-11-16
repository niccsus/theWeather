import org.json.JSONObject;

//Class for going through json files and returning specific data points
public class Forecast {
    private JSONObject json = new JSONObject();
    private int max_temp;

    public Forecast(JSONObject json){
        this.json = json;
        max_temp = this.json.getJSONObject("main").getInt("temp_max");  //get max_temp from json and save it
    }

    //return temp
    public int get_temp(){
        return max_temp;
    }
}
