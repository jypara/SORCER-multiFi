import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface ParkingLot{
	
	public Context availableSpace(Context context) throws RemoteException, ContextException;

	public Context directions(Context context) throws RemoteException, ContextException;

 }