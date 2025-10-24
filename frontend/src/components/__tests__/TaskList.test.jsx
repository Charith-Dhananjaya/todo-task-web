import { render, fireEvent } from '@testing-library/react';
import TaskList from '../TaskList';

describe('TaskList', () => {

  it('renders list of tasks', () => {
    const tasks = [
      { id: 1, title: 'A', description: 'a', completed: false },
      { id: 2, title: 'B', description: 'b', completed: false },
    ];
    const utils = render(<TaskList tasks={tasks} onMarkDone={() => {}} />);
    const list = utils.getByTestId('task-list');
  
    expect(list.querySelectorAll('[data-testid="task-card"]').length).toBe(2);

  });

  it('renders empty state', () => {

    const utils = render(<TaskList tasks={[]} onMarkDone={() => {}} />);
    expect(utils.getByTestId('no-task')).toHaveTextContent('No tasks available.');

  });

  it('propagates Done click up', () => {

    const tasks = [{ id: 10, title: 'X', description: 'x', completed: false }];

    const onMarkDone = vi.fn();
    
    const utils = render(<TaskList tasks={tasks} onMarkDone={onMarkDone} />);

    const btn = utils.getByTestId('done-btn-10');
    fireEvent.click(btn);
    expect(onMarkDone).toHaveBeenCalledWith(10);

  });
});
