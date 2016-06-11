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
    double Period_copy, Amp_copy, Step_Size_copy;
    int Method;
    
    //Funkcja wyliczająca wartości dla pobudzeń
    double Stimulation(int Stimulation_Type, double Value_copy, double Step_Size_copy) {
        switch (Stimulation_Type) {
            //Pobudzenie prostokątne
            case 0:

                if (Math.sin(2 * Math.PI * Value_copy / Period_copy) > 0) {
                    return Amp_copy;
                } else {
                    return -Amp_copy;
                }
            //Pobudzenie Trójkątne    
            case 1:

                return (Amp_copy * Math.asin(Math.sin(2 * Math.PI * (Value_copy / Period_copy))));
            //Pobudzenie Sinusoidalne    
            case 2:

                return (Amp_copy * Math.sin(2 * Math.PI * (Value_copy / Period_copy)));
            //Pobudzenie Deltą Diraca    
            case 3:

                if (Value_copy <= Step_Size_copy) {
                    return 1 / Step_Size_copy;
                } else {
                    return 0;
                }
            default:
                break;
        }
        return 0;
    }

    //Implementacja całkowań
    double integrale(double t, double FunctionValue, double PreviousFunctionValue, double PrePreviousFunctionValue, double PreviousIntegral) {
        switch (Method) {
            //Metoda prostkoątów
            case 0: {
                double answer = 0;
                answer = PreviousIntegral + Step_Size_copy * FunctionValue;
                return answer;
            }
            //Metoda paraboli
            case 1: {
                double answer = 0;
                answer = PreviousIntegral + (Step_Size_copy / 6) * (FunctionValue + 4 * PreviousFunctionValue + PrePreviousFunctionValue);
                return answer;
            }
            //Metoda trapezów
            case 2: {
                double answer = 0;
                answer = PreviousIntegral + 0.5 * Step_Size_copy * (FunctionValue + PreviousFunctionValue);
                return answer;
            }
            default:
                break;
        }
        return 0;
    }
    //Modelowanie układu
    double SystemAnswer(double t){
        return;
    }
    double SystemErr(double t){
        return;
    }
}
