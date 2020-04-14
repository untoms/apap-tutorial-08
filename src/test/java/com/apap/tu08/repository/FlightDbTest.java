package com.apap.tu08.repository;

import com.apap.tu08.model.FlightModel;
import com.apap.tu08.model.PilotModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightDbTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightDb flightDb;

    @Test
    public void whenFindByFlightNumber_thenReturnFlight(){
        //Given
        PilotModel pilotModel = new PilotModel();
        pilotModel.setLicenseNumber("1234");
        pilotModel.setName("Anto");
        pilotModel.setFlyHour(50);

        entityManager.persist(pilotModel);
        entityManager.flush();

        FlightModel flightModel = new FlightModel();
        flightModel.setFlightNumber("1765");
        flightModel.setOrigin("Jakarta");
        flightModel.setDestination("Bali");
        flightModel.setTime(new Date(System.currentTimeMillis()));
        flightModel.setPilot(pilotModel);

        entityManager.persist(flightModel);
        entityManager.flush();

        //When
        Optional<FlightModel> found = flightDb.findByFlightNumber(flightModel.getFlightNumber());

        //Then
        assertThat(found.get(), Matchers.notNullValue()); //check if not null
        assertThat(found.get(), Matchers.equalTo(flightModel)); //check if same

    }
}
