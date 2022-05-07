package drytron.cep_api;

/**
 *
 * @author dayon
 */
public class Main {
    public static void main(String[] args) {
        ViaCEP vc = new ViaCEP();
        try {
            vc.buscar("83702360");
            System.out.println(vc.getBairro());
             System.out.println(vc.getLocalidade());
           
            
            
            
        } catch (ViaCEPException ex) {
        }
    }
}
