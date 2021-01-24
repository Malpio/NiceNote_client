package com.company.server;

import com.company.common.TextFile;
import com.company.common.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface INiceNoteServer extends Remote {
    User login(String email, String password) throws RemoteException;
    boolean registry(String firstName, String lastName, String email, String password) throws RemoteException;
    Integer createFile(Integer userId, String fileName) throws RemoteException;
    List<TextFile> filesList(Integer userId) throws RemoteException;
    String readFile(Integer userId, String fileName) throws RemoteException;
    void writeFile(Integer userId, String fileName, String text) throws RemoteException;
}


