package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.model.Author;
import com.furkanbilgin.week3.springmvc.repository.AuthorRepository;
import com.furkanbilgin.week3.springmvc.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> updateAuthor(Long id, Author author) {
        return authorRepository
                .findById(id)
                .map(
                        existingAuthor -> {
                            existingAuthor.setName(author.getName());
                            existingAuthor.setEmail(author.getEmail());
                            return authorRepository.save(existingAuthor);
                        });
    }

    @Override
    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
