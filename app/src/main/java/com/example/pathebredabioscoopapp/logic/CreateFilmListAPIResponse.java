package com.example.pathebredabioscoopapp.logic;

import java.util.List;

/**
 * Dit object komt overeen met het response zoals je dat van de API in het response ontvangt.
 * Je maakt dus voor een API, voor de JSON die je ontvangt, een specifiek response class.
 *
 * {
 *     "status_code": 1,
 *     "status_message": "The item/record was created successfully.",
 *     "success": true,
 *     "list_id": 7086750
 * }
 *
 *
 */
public class CreateFilmListAPIResponse {

    private final int status_code;
    private final String status_message;
    private final int list_id;

    public CreateFilmListAPIResponse(int status_code, String status_message, int list_id) {
        this.status_code = status_code;
        this.status_message = status_message;
        this.list_id = list_id;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public int getList_id() {
        return list_id;
    }

    @Override
    public String toString() {
        return "CreateFilmListApiResponse{" +
                "status_code=" + status_code +
                ", status_message='" + status_message + '\'' +
                ", list_id=" + list_id +
                '}';
    }
}


