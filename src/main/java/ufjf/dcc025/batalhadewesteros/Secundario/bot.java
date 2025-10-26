package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.ArrayList;
import java.util.List;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.linnister;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.stark;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.targaryen;

public class bot {       
    
    /// Crriei apenas para o teste e configuracao do replay
   private static List<personagem> timeBot;
    
    public bot(){
        
        timeBot = new ArrayList<>();
        
        stark p1 = new stark("BOT: stark");
        linnister p2 = new linnister("BOT: linnister");
        targaryen p3 = new targaryen("BOT: targaryen");
        
        timeBot.add(p1);
        timeBot.add(p2);
        timeBot.add(p3);
    }
    
    
    ///
    ///Seria interessante usarmos um metodo statico pois nao precisaremos instanciar
    ///um objeto do tipo bot para ultilizar seus metodos
    /// 
    public static List<personagem> getPersonagem(){return timeBot;} 
}
