import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface Payment{
	
	public Context payYourWay(Context context) throws RemoteException, ContextException;

	public Context timeIsMoney(Context context) throws RemoteException, ContextException;

 }