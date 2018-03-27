package seedu.address.commons.event;

import org.junit.Test;

import seedu.address.commons.events.ui.NewAppointmentAddedEvent;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

public class NewAppointmentAddedEventTest {

    @Test
    public void getAppointmentAdded_valid_success() {
        Appointment appointment = new AppointmentBuilder().build();
        NewAppointmentAddedEvent event = new NewAppointmentAddedEvent(appointment);
        assert(event.getAppointmentAdded().equals(appointment));
    }
}
