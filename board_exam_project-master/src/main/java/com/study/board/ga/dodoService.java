package com.study.board.ga;

import com.study.board.ga.dodo;
import com.study.board.repository.dodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class dodoService {

    @Autowired
    private dodoRepository dodoRepository;



    public void write(dodo dodo) throws Exception {
        dodoRepository.save(dodo);
    }

    public Page<dodo> dodoList(Pageable pageable) {

        return dodoRepository.findAll(pageable);
    }

    public Page<dodo> dodoSearchList(String searchKeyword, Pageable pageable) {

        return dodoRepository.findByDonameContaining(searchKeyword, pageable);
    }





}

