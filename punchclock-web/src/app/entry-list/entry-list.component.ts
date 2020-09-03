import {Component, OnInit} from '@angular/core';
import {EntryService} from "../service/entry.service";
import {EntryModel} from "../domain/Entry.model";

@Component({
  selector: 'app-entry-list',
  templateUrl: './entry-list.component.html',
  styleUrls: ['./entry-list.component.scss']
})
export class EntryListComponent implements OnInit {

  entries: EntryModel[];
  displayedColumns: string[] = ['checkIn', 'checkOut', 'category'];

  constructor(private entryService: EntryService) {
  }

  ngOnInit(): void {
    this.entryService.getAllEntries().subscribe(entries => this.entries = entries);
  }


}
