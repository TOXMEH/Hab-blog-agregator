package ru.dvfu.agregator.controller;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dvfu.agregator.service.ArticleService;

/**
 * Created by nesud on 02.12.2016.
 */
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    private MockMvc mockMvc;

    @InjectMocks
    private ArticleController articleController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }


}