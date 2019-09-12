package com.example.demo.taskengine.models.base;

import javax.persistence.*;


@MappedSuperclass
public abstract class Task<C extends Context, T extends Task> {

    @OneToOne
    protected C context;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobDefinitionBean;
    private boolean active;

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public C getContext() {
        return context;
    }

    public T setContext(C context) {
        this.context = context;
        return (T) this;
    }

    public abstract void execute();

    public String getJobDefinitionBean() {
        return jobDefinitionBean;
    }

    public Task<C, T> setJobDefinitionBean(String jobDefinitionBean) {
        this.jobDefinitionBean = jobDefinitionBean;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Task<C, T> setActive(boolean active) {
        this.active = active;
        return this;
    }
}
