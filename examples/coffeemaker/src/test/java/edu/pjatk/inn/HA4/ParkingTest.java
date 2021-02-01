import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class ParkingTest{
	
	@Test
	public void parkingStructuredBlock() throws Exception {

		Task parkingSpace = task("parkingSpace", sig("availableSpace", ParkingLot.class),
			context("availableSpace", inVal("arg/car"),
				result("arg/parkingSpot1", Signature.Direction.IN)));

		Task directToSpot = task("directToSpot", sig("directions", ParkingLot.class),
			context("directions", inVal("arg/parkingSpot1"), inVal("arg/distance1", "arg/directionF"),
				inVal("arg/distance3", "arg/directionR"),inVal("arg/distance4", "arg/directionL"),
				result("arg/DTS", Signature.Direction.IN)));

		Task parkAtSpot = task("parkAtSpot", sig("parkIn", Car.class),
			context("parkIn", inVal("arg/DTS"),
				result("arg/tokenNumber", Signature.Direction.IN))); // Token Number generated is registered with the Car, checked during BackOut.

		Task payForTime = task("parkAtSpot", sig("timeIsMoney", Payment.class),
			context("timeIsMoney", inVal("arg/tokenNumber"), inVal("arg/timeStart"), inVal("arg/timeEnd")
				result("arg/totalTime", Signature.Direction.IN)));

		Task timeToPay = task("timeToPay", sig("payYourWay", Payment.class),
			context("payYourWay", inVal("arg/internetBanking"), inVal("arg/totaltime"),
				result("arg/Paid", Signature.Direction.IN)));

		Task letsGo = task("letsGo", sig("backOut", Car.class),
			context("backOut", inVal("arg/distance4", "arg/directionB"), inVal("arg/distance3", "arg/directionL"),
				inVal("arg/distance1", "arg/directionF"),
				result("block/routineCompleted", Signature.Direction.OUT)));

		Block block = block("block", block(parkingSpace, directToSpot), parkAtSpot, block(payForTime, timeToPay), letsGo,
			context(inval("car", "WX12M01"), inval("parkingSpot1", "A15"),
					inval("distance1", 100), inval("distance2", 20),
					inval("distance3", 50), inval("distance4", 5),
					inval("directionR", "Right"),inval("directionL", "Left"),
					inval("directionF", "Forward"), inval("directionB", "Backward"),
					inval("timeStart", "1130"), inval("timeEnd", "1830"),
					inval("internetBanking", X1234)));

		Block result = exert(block);
		logger.info("result: " + result);
		assertEquals(value(context(result), "block/routineCompleted"), "A15"); // Now A15 is Available Again.
	}
}