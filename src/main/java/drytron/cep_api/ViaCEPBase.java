package drytron.cep_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public abstract class ViaCEPBase {

    protected List<CEP> CEPs;
    protected int index;
    protected String currentCEP;

    protected ViaCEPEvents Events;

    public ViaCEPBase() {
        CEPs = new ArrayList<>();
        index = -1;
        currentCEP = "00000-000";
        this.Events = null;
    }

    // métodos abstratos
    public abstract void buscar(String cep);

    public abstract void buscarCEP(CEP cep);

    public void buscarCEP(String Uf, String Localidade, String Logradouro) {
        buscarCEP(new CEP(Logradouro, Localidade, Uf));
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return CEPs.size();
    }

    public String getCep() {
        return CEPs.get(index).CEP;
    }

    public String getLogradouro() {
        return CEPs.get(index).Logradouro;
    }

    public String getComplemento() {
        return CEPs.get(index).Complemento;
    }

    public String getBairro() {
        return CEPs.get(index).Bairro;
    }

    public String getLocalidade() {
        return CEPs.get(index).Localidade;
    }

    public String getUf() {
        return CEPs.get(index).Uf;
    }

    public String getIbge() {
        return CEPs.get(index).Ibge;
    }

    public String getGia() {
        return CEPs.get(index).Gia;
    }

    public final String getHttpGET(String urlToRead) {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {
            // verifica os Eventos
            if (Events instanceof ViaCEPEvents) {
                Events.onCEPError(currentCEP);
            }

        } catch (IOException ex) {
            // verifica os Eventos
            if (Events instanceof ViaCEPEvents) {
                Events.onCEPError(currentCEP);
            }

        }

        return result.toString();
    }

    public boolean move(int index) {
        if (CEPs.size() > 0 && index >= 0 && index < CEPs.size()) {
            this.index = index;
            return true;
        }

        this.index = -1;
        return false;
    }

    public boolean moveFirst() {
        if (!CEPs.isEmpty()) {
            index = 0;
            return true;
        }

        index = -1;
        return false;
    }

    public boolean moveNext() {
        if (!CEPs.isEmpty() && (index + 1) < CEPs.size()) {
            index += 1;
            return true;
        }

        index = -1;
        return false;
    }

    public boolean movePrevious() {
        if (!CEPs.isEmpty() && (index - 1) >= 0) {
            index -= 1;
            return true;
        }

        index = -1;
        return false;
    }

    public boolean moveLast() {
        if (!CEPs.isEmpty()) {
            index = CEPs.size() - 1;
            return true;
        }

        index = -1;
        return false;
    }

    public List<CEP> getList() {
        return CEPs;
    }

    protected String formatStringToUri(String string) {
        String out = null;

        if (string != null && !string.isEmpty()) {
            try {
                out = URLEncoder.encode(string, "utf-8");
                out = out.replace("+", "%20");
            } catch (UnsupportedEncodingException e) {
                System.out.println("Não foi possível codificar o valor solicitado!");
            }
        } else {
            System.out.println("Valor nulo ou vazio informado!");
        }

        return out;
    }
}
