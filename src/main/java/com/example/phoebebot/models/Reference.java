package com.example.phoebebot.models;

import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;

public class Reference implements IReference {

    private IReferenceCommandDTO usedCommand;
    private Platforms platform;
    private String platformMainPage;
    private String platformIconUrl;
    private String author;
    private String url;
    private String data;
    private int width;
    private int height;

    public Reference() {

    }

    public Reference(IReferenceCommandDTO usedCommand, Platforms platform, String platformMainPage, String platformIconUrl, String author, String url, String data, int width, int height) {
        this.usedCommand = usedCommand;
        this.platform = platform;
        this.platformMainPage = platformMainPage;
        this.platformIconUrl = platformIconUrl;
        this.author = author;
        this.url = url;
        this.data = data;
        this.width = width;
        this.height = height;
    }

    @Override
    public IReferenceCommandDTO getUsedCommand() {
        return usedCommand;
    }

    @Override
    public String getPlatformIconUrl() {
        return platformIconUrl;
    }

    @Override
    public String getPlatformMainPage() {
        return platformMainPage;
    }

    @Override
    public Platforms getPlatform() {
        return platform;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "usedCommand=" + usedCommand +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", data='" + data + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
