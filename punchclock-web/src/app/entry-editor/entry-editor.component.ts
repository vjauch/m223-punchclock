import {Component, Input, OnInit} from '@angular/core';
import {EntryModel} from "../domain/Entry.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectModel} from "../domain/Project.model";
import {ProjectService} from "../service/project.service";

@Component({
  selector: 'app-entry-editor',
  templateUrl: './entry-editor.component.html',
  styleUrls: ['./entry-editor.component.scss']
})
export class EntryEditorComponent implements OnInit {

  date: Date;
  entryForm: FormGroup;

  @Input()
  entry: EntryModel;
  projects: ProjectModel[];

  constructor(private formBuilder: FormBuilder,
              private projectService: ProjectService) { }

  ngOnInit(): void {
    this.entryForm = this.formBuilder.group({
      checkIn: null,
      password: ''
    })
    this.projectService.getProjects().subscribe(projects => this.projects = projects);
  }

  public saveEntry(EntryModel) {

  }
}
