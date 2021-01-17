package com.company.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INiceNoteServer extends Remote {
    Integer login(String email, String password) throws RemoteException;
    boolean registry(String firstName, String lastName, String email, String password) throws RemoteException;
}


