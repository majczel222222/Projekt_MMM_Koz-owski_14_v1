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
     double w;
    private double x2;
    private double x2derivative;
    private double Previousx2;
    private double PrePreviousx2;
    private double Previousx1;
     double x1;
    private double PrePreviousx1;
    private double x2derivativePrevious;
    private double x2derivativePrePrevious;
    private double k;

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

    double NonLinearBlock(double t) {
        
        k = A_copy/a_copy;
        
        if(a_copy/Amp_copy < -1){
            w = -k;
        }
        else if(Math.abs(a_copy/Amp_copy) <= 1){
            w = (((k*2)/Math.PI)*Math.asin(a_copy/Amp_copy))+(a_copy/Amp_copy)*Math.sqrt(1+((a_copy*a_copy)/(Amp_copy*Amp_copy)));
        }
        else if(a_copy/Amp_copy > 1){
            w = k;
        }

        return w;
    }

    double SystemAnswer(double t) {

        x2derivative = ((-1 / T_copy) * x2) + ((1 / T_copy) * w);

        x1 = integrale(t, x2, Previousx2, PrePreviousx2, Previousx1);
        PrePreviousx1 = Previousx1;
        Previousx1 = x1;

        x2 = integrale(t, x2derivative, x2derivativePrevious, x2derivativePrePrevious, Previousx2);
        PrePreviousx2 = Previousx2;
        Previousx2 = x2;

        x2derivativePrePrevious = x2derivativePrevious;
        x2derivativePrevious = x2derivative;

        return x1;
    }
}
