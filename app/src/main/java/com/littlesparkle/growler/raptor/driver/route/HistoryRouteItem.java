package com.littlesparkle.growler.raptor.driver.route;

public class HistoryRouteItem {
    public int status;
    public String status_description;
    public String start_point;
    public String end_point;
    public String start_time;
    public String end_time;
    public String route_type;

    public HistoryRouteItem(int status, String status_description, String start_point,
                            String end_point, String start_time, String end_time,
                            String route_type) {
        this.status = status;
        this.status_description = status_description;
        this.start_point = start_point;
        this.end_point = end_point;
        this.start_time = start_time;
        this.end_time = end_time;
        this.route_type = route_type;
    }
}
