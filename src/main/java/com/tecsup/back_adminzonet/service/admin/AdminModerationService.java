package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.entity.CommunityPost;
import com.tecsup.back_adminzonet.repository.CommunityPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminModerationService {

    @Autowired
    private CommunityPostRepository communityRepo;

    public List<CommunityPost> listAllPosts() {
        return communityRepo.findAll();
    }

    public void deletePost(Long postId) {
        communityRepo.deleteById(postId);
    }
}