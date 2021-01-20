package com.example.booktracker.controller;


import com.example.booktracker.exception.ResourceNotFoundException;
import com.example.booktracker.model.Reader;
import com.example.booktracker.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReaderController {
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/readers")
    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    @GetMapping("/readers/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(reader);

    }

    @PostMapping("/readers/add")
    public Reader addReader( @RequestBody Reader reader){
        return readerRepository.save(reader);
    }

    @PutMapping("/readers/update/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable(value = "id") Integer id, @RequestBody Reader readerDetails)
            throws ResourceNotFoundException{
        Reader reader = readerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book not found for this id :: " + id));

        reader.setId(readerDetails.getId());
        reader.setName(readerDetails.getName());
        reader.setWeeklyGoal(readerDetails.getWeeklyGoal());
        reader.setMinutesRead(readerDetails.getMinutesRead());
        final Reader updatedReader = readerRepository.save(reader);
        return ResponseEntity.ok(updatedReader);

    }

    @DeleteMapping("/readers/delete/{id}")
    public Map<String,Boolean>deleteReader(@PathVariable(value = "id") Integer id )
            throws ResourceNotFoundException{
        Reader reader = readerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" Book not found by this id :: " + id));

        readerRepository.delete(reader);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}

