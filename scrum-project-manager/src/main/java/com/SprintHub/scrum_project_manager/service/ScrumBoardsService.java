package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.ScrumBoards;
import com.SprintHub.scrum_project_manager.model.UserStories;
import com.SprintHub.scrum_project_manager.repository.ScrumBoardsJoin;
import com.SprintHub.scrum_project_manager.repository.ScrumBoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScrumBoardsService {
    @Autowired
    private ScrumBoardsRepository scrumBoardsRepository;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private UserStoriesService userStoriesService;

    public List<ScrumBoards> createScrumBoard(ScrumBoards scrumBoards, String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        List<ScrumBoards> saveScrumBoardsList = new ArrayList<>();
        for (int i = 0; i < scrumBoards.getArrayTokenUserHistory().length; i++) {
            UserStories userStories = userStoriesService.getUserStoriesByTokenHu(scrumBoards.getArrayTokenUserHistory()[i]);
            ScrumBoards newScrumBoard = new ScrumBoards();
            newScrumBoard.setStartDateScrumboard(scrumBoards.getStartDateScrumboard());
            newScrumBoard.setEndDateScrumboard(scrumBoards.getEndDateScrumboard());
            newScrumBoard.setSpringNumberScrumboard(scrumBoards.getSpringNumberScrumboard());
            newScrumBoard.setUserStoryId(userStories.getIdUserStory());
            newScrumBoard.setProjectId(projects.getIdProject());

            ScrumBoards saveScrumBoards = scrumBoardsRepository.save(newScrumBoard);
            saveScrumBoardsList.add(saveScrumBoards);

        }
        return saveScrumBoardsList;
    }

    public List<ScrumBoardsJoin> getAllHuScrumboard() {
        return scrumBoardsRepository.getAllHuScrumboard();
    }
}
