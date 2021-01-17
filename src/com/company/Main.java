package com.company;

import com.company.server.INiceNoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        INiceNoteServer niceNoteServer = (INiceNoteServer) registry.lookup("NiceNoteServer");

        Integer id = niceNoteServer.login("admin@admin.pl", "amin");
        System.out.println(id);
    }
}
