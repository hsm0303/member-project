package com.example.member.dto;

import java.util.List;

public class MemberPageResponse {

    private List<MemberResponse> members;
    private int currentPage;
    private int pageSize;
    private long totalMembers;
    private int totalPages;

    public MemberPageResponse(List<MemberResponse> members, int currentPage, int pageSize, long totalMembers, int totalPages) {
        this.members = members;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalMembers = totalMembers;
        this.totalPages = totalPages;
    }

    public List<MemberResponse> getMembers() {
        return members;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
