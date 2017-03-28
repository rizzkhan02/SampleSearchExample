package com.search.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.search.Descriptor.FinalOutput;
import com.search.handler.SearchHandler;

@RestController
@RequestMapping(value = "Search")
public class SearchService {
	
	@Autowired
	SearchHandler searchHandler;
	
	@RequestMapping(value = "{key}", method = RequestMethod.GET)
    public FinalOutput searchConference(@PathVariable("key") String key) throws IOException {		
      return searchHandler.searchKeywordInLogs(key);
    }
	

}
