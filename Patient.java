import java.util.ArrayList;
import java.util.List;

// Observer interface for healthcare providers
interface HealthCareProvider {
    void update(Patient patient);
}

// ConcreteObserver representing a healthcare provider
class Doctor implements HealthCareProvider {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    @Override
    public void update(Patient patient) {
        System.out.println("Doctor " + name + " is notified about the patient's vital signs:");
        System.out.println("Heart Rate: " + patient.getHeartRate());
        System.out.println("Blood Pressure: " + patient.getBloodPressure());
        System.out.println("Respiratory Rate: " + patient.getRespiratoryRate());
    }
}

// Subject interface representing a patient being monitored
interface Patient {
    void registerHealthCareProvider(HealthCareProvider provider);

    void removeHealthCareProvider(HealthCareProvider provider);

    void notifyHealthCareProviders();
    
    int getHeartRate();

    int getBloodPressure();

    int getRespiratoryRate();
}

// ConcreteSubject representing a patient being monitored
class RealTimePatient implements Patient {
    private List<HealthCareProvider> providers = new ArrayList<>();
    private int heartRate;
    private int bloodPressure;
    private int respiratoryRate;

    @Override
    public void registerHealthCareProvider(HealthCareProvider provider) {
        providers.add(provider);
    }

    @Override
    public void removeHealthCareProvider(HealthCareProvider provider) {
        providers.remove(provider);
    }

    @Override
    public void notifyHealthCareProviders() {
        for (HealthCareProvider provider : providers) {
            provider.update(this);
        }
    }

    @Override
    public int getHeartRate() {
        return heartRate;
    }

    @Override
    public int getBloodPressure() {
        return bloodPressure;
    }

    @Override
    public int getRespiratoryRate() {
        return respiratoryRate;
    }

    // Simulate vital signs change in a real-time system
    public void updateVitalSigns(int heartRate, int bloodPressure, int respiratoryRate) {
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.respiratoryRate = respiratoryRate;
        notifyHealthCareProviders();
    }
}

public class HealthCareObserverPattern {
    public static void main(String[] args) {
        RealTimePatient patient = new RealTimePatient();

        HealthCareProvider doctor1 = new Doctor("Dr. Smith");
        HealthCareProvider doctor2 = new Doctor("Dr. Johnson");

        patient.registerHealthCareProvider(doctor1);
        patient.registerHealthCareProvider(doctor2);

        // Simulate changes in patient's vital signs
        patient.updateVitalSigns(75, 120, 18);
        patient.updateVitalSigns(80, 118, 20);

        // Unregister a doctor
        patient.removeHealthCareProvider(doctor2);

        // Simulate more changes
        patient.updateVitalSigns(90, 130, 22);
    }
}
