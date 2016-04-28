/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author majcz_000
 */
public class Calculations {
    
    double A_copy, a_copy, T_copy;
    double Period_copy, Amp_copy;    
    double Stimulation(int Stimulation_Type, double Value_copy, double Step_Size_copy){
        if(Stimulation_Type == 0 )                                              //square
        {
            if(Math.sin(2*Math.PI*Value_copy/Period_copy)>0) return Amp_copy;
            else return -Amp_copy;
        }
        else if(Stimulation_Type == 1){                                         //triangle
            return (Amp_copy*Math.asin(Math.sin(2*Math.PI*(Value_copy/Period_copy))));
        }
        else if(Stimulation_Type == 2){                                         //sin
            return(Amp_copy*Math.sin(2*Math.PI*(Value_copy/Period_copy)));
        }
    return 0;
    }
}

    
