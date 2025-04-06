package de.pmoit.grundlagen.softwaretesting.versicherungsapi;

import java.net.SocketTimeoutException;

import org.json.JSONObject;

import de.pmoit.grundlagen.softwaretesting.Auto;
import de.pmoit.grundlagen.softwaretesting.utils.rest.JSONService;
import de.pmoit.grundlagen.softwaretesting.versicherungsapi.exceptions.AKKEHttpError;


public class VersicherungAKKE implements IVersicherung {
    String tarif;
    int tarifpreis;
    final String APIKey;
    String host = "https://api.akke-versicherung.de";
    private JSONService jsonService = new JSONService();

    public VersicherungAKKE(String APIKey) {
        this.APIKey = APIKey;
    }

    public VersicherungAKKE(String host, String APIKey) {
        this.host = host;
        this.APIKey = APIKey;
    }

    @Override
    public String getTarifName(Auto auto) {
        JSONObject jsonObjectTarif;
        try {
            jsonObjectTarif = getJasonObjectTarif(auto);
        } catch (AKKEHttpError e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return jsonObjectTarif.getString("tarif-name");
    }

    @Override
    public int getTarifPreis(Auto auto) {
        JSONObject jsonObjectTarif;
        try {
            jsonObjectTarif = getJasonObjectTarif(auto);
        } catch (AKKEHttpError e) {
            e.printStackTrace();
            return 0;
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return 0;
        }
        return jsonObjectTarif.getInt("tarif-preis");
    }

    private JSONObject getJasonObjectTarif(Auto auto) throws AKKEHttpError, SocketTimeoutException {
        JSONObject jsonObject = getAKKEApiResult(auto);
        return jsonObject.getJSONObject("tarif");
    }

    private JSONObject getAKKEApiResult(Auto auto) throws AKKEHttpError, SocketTimeoutException {
        String apicall = host + "/abfrage?q=" + auto.getKennzeichen() + "&apiKey=" + APIKey;
        String result = jsonService.getJSONRequestResult(apicall);
        if (result.equals("Fehler : HTTP error code : 500")) {
            throw new AKKEHttpError(result);
        }
        return new JSONObject(result);
    }

}
