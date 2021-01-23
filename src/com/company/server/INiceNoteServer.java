package com.company.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface INiceNoteServer extends Remote {
    Integer login(String email, String password) throws RemoteException;
    boolean registry(String firstName, String lastName, String email, String password) throws RemoteException;
    Integer createFile(Integer userId, String fileName) throws RemoteException;
    List<String> filesList(Integer userId) throws RemoteException;
}


