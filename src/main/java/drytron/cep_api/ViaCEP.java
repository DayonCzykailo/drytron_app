package drytron.cep_api;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViaCEP extends ViaCEPBase {

    public static final double VIACEP_VERSAO = 0.33;

    public ViaCEP() {
        super();
    }

    public ViaCEP(ViaCEPEvents events) {
        super();
        this.Events = events;
    }

    public ViaCEP(String cep, ViaCEPEvents events) {
        super();
        this.Events = events;
        this.buscar(cep);
    }

    public ViaCEP(String cep) {
        super();
        this.buscar(cep);
    }

    @Override
    public final void buscar(String cep) {
        currentCEP = cep;
        cep = cep.replaceAll("-", "");
        cep = cep.replace(" ", "");

        String url = "http://viacep.com.br/ws/" + cep + "/json/";

        JSONObject obj = new JSONObject(getHttpGET(url));

        if (!obj.has("erro")) {
            CEP novoCEP = new CEP(obj.getString("cep"),
                    obj.getString("logradouro"),
                    obj.getString("complemento"),
                    obj.getString("bairro"),
                    obj.getString("localidade"),
                    obj.getString("uf"),
                    obj.getString("ibge"),
                    obj.getString("gia"));

            CEPs.add(novoCEP);

            index = CEPs.size() - 1;

            if (Events instanceof ViaCEPEvents) {
                Events.onCEPSuccess(this);
            }
        } else {
            if (Events instanceof ViaCEPEvents) {
                Events.onCEPError(currentCEP);
            }

            System.out.println("Não foi possível encontrar o CEP");
        }
    }

    @Override
    public void buscarCEP(CEP cep) {
        buscarCEP(cep.Uf, cep.Localidade, cep.Logradouro);
    }

    @Override
    public void buscarCEP(String Uf, String Localidade, String Logradouro) {
        currentCEP = "?????-???";

        String url = "http://viacep.com.br/ws/" + Uf.toUpperCase() + "/" + Localidade + "/" + Logradouro + "/json/";

        JSONArray ceps = new JSONArray(getHttpGET(url));

        if (ceps.length() > 0) {
            for (int i = 0; i < ceps.length(); i++) {
                JSONObject obj = ceps.getJSONObject(i);

                if (!obj.has("erro")) {
                    CEP novoCEP = new CEP(obj.getString("cep"),
                            obj.getString("logradouro"),
                            obj.getString("complemento"),
                            obj.getString("bairro"),
                            obj.getString("localidade"),
                            obj.getString("uf"),
                            obj.getString("ibge"),
                            obj.getString("gia"));

                    CEPs.add(novoCEP);

                    index = CEPs.size() - 1;

                    if (Events instanceof ViaCEPEvents) {
                        Events.onCEPSuccess(this);
                    }
                } else {
                    if (Events instanceof ViaCEPEvents) {
                        Events.onCEPError(currentCEP);
                    }

                    System.out.println("Não foi possível validar o CEP" + currentCEP);
                }
            }
        } else {
            System.out.println("Nenhum CEP encontrado" + currentCEP);
        }
    }
}
